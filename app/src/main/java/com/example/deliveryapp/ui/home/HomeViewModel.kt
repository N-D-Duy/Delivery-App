package com.example.deliveryapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.data.AppData
import com.example.deliveryapp.data.local.database.AppDatabase
import com.example.deliveryapp.data.local.database.AppDbHelper
import com.example.deliveryapp.data.local.database.DbHelper
import com.example.deliveryapp.model.User
import com.example.deliveryapp.utils.UiState
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val _localDatabase: AppDatabase,
    private val _remoteDatabase: FirebaseFirestore
) : ViewModel() {
    private val dataManager = AppData(_localDatabase, _remoteDatabase)

    private val _users = MutableLiveData<UiState<List<User?>?>>()
    val users: LiveData<UiState<List<User?>?>> get() = _users

    fun getUsers(){
        /*viewModelScope.launch {
            try {
                // Gọi phương thức của DataManager để lấy danh sách người dùng
                val users = dataManager.getUsers { result ->
                    when (result) {
                        is UiState.Success -> {
                            // Cập nhật trạng thái UI với dữ liệu nhận được
                            _users.value = result
                        }

                        is UiState.Error -> {
                            _users.value = UiState.Error("Unknown error")
                        }

                        is UiState.Loading -> {

                        }
                    }

                }



            } catch (e: Exception) {
                // Xử lý lỗi nếu có
                _users.value = UiState.Error(e.message ?: "Unknown error")
            }
        }*/
    }
}