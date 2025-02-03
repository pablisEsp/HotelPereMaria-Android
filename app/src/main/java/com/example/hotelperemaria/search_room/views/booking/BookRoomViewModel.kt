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
import java.time.format.DateTimeFormatter
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
                    updateState { it.copy(startDate = event.value) }
            }
            is BookRoomEvent.AddEndDate -> {
                    updateState { it.copy(endDate = event.value) }
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

            is BookRoomEvent.SearchRooms -> {
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                val startDate = LocalDate.parse(_bookRoomState.value.startDate,formatter)
                val endDate = LocalDate.parse(_bookRoomState.value.endDate,formatter)
                if (startDate.isBefore(LocalDate.now())){
                    updateState { it.copy(snackBarMessage = "Has introducido una fecha anterior al dia de hoy", snackBarIsShown = true) }
                }
                if (startDate.isAfter(endDate)){
                    updateState { it.copy(snackBarMessage = "Has seleccionado una fecha de salida anterior a la fecha de entrada", snackBarIsShown = true) }
                }
                viewModelScope.launch {
                    // Example: Trigger navigation or API call
                }
            }

            is BookRoomEvent.ShowSnackBar -> {
                updateState { it.copy(snackBarIsShown = true)
                        it.copy(snackBarMessage = event.value)}
            }
            is BookRoomEvent.HideSnackBar ->{
                updateState { it.copy(snackBarIsShown = !event.value) }
            }
        }
    }



    // Helper function to update the state safely
    private fun updateState(update: (BookRoomState) -> BookRoomState) {
        val currentState = _bookRoomState.value
        _bookRoomState.value = update(currentState)
    }


    init {
        // Initialize default values
        _bookRoomState.value = BookRoomState(
            startDate = formateador(LocalDate.now()),
            endDate = formateador(LocalDate.now().plusDays(2)), // Default end date is tomorrow
            numberOfGuests = 1,
            snackBarIsShown = false,
            snackBarMessage = "This is an example text"
        )
    }
}

fun formateador(localDate: LocalDate):String{
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return localDate.format(formatter)
}