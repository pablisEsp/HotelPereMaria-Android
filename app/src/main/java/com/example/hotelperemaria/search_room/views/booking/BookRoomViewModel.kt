package com.example.hotelperemaria.search_room.views.booking

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class BookRoomViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    // Single state object to hold all properties
    private val _bookRoomState = MutableStateFlow(BookRoomState())
    val bookRoomState = _bookRoomState.asStateFlow()

    // Function to handle events
    fun onEvent(event: BookRoomEvent) {
        when (event) {
            is BookRoomEvent.AddStartDate -> {
                Log.d("BookRoomViewModel", "Validating start date: ${event.value}")
                if (isValidStartDate(event.value)) {
                    updateState { it.copy(startDate = event.value) }
                } else {
                    emitError("Start date must be today or later")
                }
            }
            is BookRoomEvent.AddEndDate -> {
                Log.d("BookRoomViewModel", "Validating end date: ${event.value}")
                if (isValidEndDate(event.value)) {
                    updateState { it.copy(endDate = event.value) }
                } else {
                    emitError("End date must be after start date")
                }
            }

            is BookRoomEvent.AddMoreGuest -> {
                updateState { it.copy(numberOfGuests = it.numberOfGuests + event.value) }
            }

            is BookRoomEvent.QuitGuest -> {
                updateState {
                    if (it.numberOfGuests > 1) {
                        it.copy(numberOfGuests = it.numberOfGuests - event.value)
                    } else {
                        it // Prevent numberOfGuests from going below 1
                    }
                }
            }

            is BookRoomEvent.SelectDate -> {
                if (isValidStartDate(event.value)) {
                    updateState { it.copy(startDate = event.value) }
                }
            }

            is BookRoomEvent.SearchRooms -> {
                // Perform search logic here (e.g., navigate to another screen or trigger API call)
                viewModelScope.launch {
                    // Example: Trigger navigation or API call
                }
            }
        }
    }

    private fun emitError(s: String) {

    }

    // Helper function to update the state safely
    private fun updateState(update: (BookRoomState) -> BookRoomState) {
        val currentState = _bookRoomState.value
        _bookRoomState.value = update(currentState)
    }

    // Validation functions
    private fun isValidStartDate(selectedDate: String): Boolean {
        return try {
            val today = LocalDate.now()
            val selected = LocalDate.parse(selectedDate)
            selected.isEqual(today) || selected.isAfter(today)
        } catch (e: Exception) {
            false // Invalid date format
        }
    }

    private fun isValidEndDate(selectedDate: String): Boolean {
        return try {
            val currentStartDate = LocalDate.parse(bookRoomState.value.startDate)
            val selected = LocalDate.parse(selectedDate)
            selected.isAfter(currentStartDate)
        } catch (e: Exception) {
            false // Invalid date format
        }
    }

    init {
        // Initialize default values
        _bookRoomState.value = BookRoomState(
            startDate = LocalDate.now().toString(),
            endDate = LocalDate.now().plusDays(1).toString(), // Default end date is tomorrow
            numberOfGuests = 1
        )
    }
}