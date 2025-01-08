package com.example.pumpfit.model.mock

import com.example.pumpfit.R
import com.example.pumpfit.model.User

val mockUsers = listOf(
    User(
        id = "1",
        image = R.drawable.ic_profile_placeholder,
        name = "João",
        email = "joao@example.com",
        password = "123456",
        weight = 70.0,
        height = 1.75,
        period = "cutting"
    ),
    User(
        id = "2",
        image = R.drawable.ic_profile_placeholder,
        name = "Maria",
        email = "maria@example.com",
        password = "abcdef",
        weight = 60.0,
        height = 1.65,
        period = "maintenance"
    ),
    User(
        id = "3",
        image = R.drawable.ic_profile_placeholder,
        name = "Carlos",
        email = "carlos@example.com",
        password = "senha123",
        weight = 80.0,
        height = 1.80,
        period = "bulking"
    ),
    User(
        id = "4",
        image = R.drawable.ic_profile_placeholder,
        name = "Gustavo Erick",
        email = "gustaerick@example.com",
        password = "senha123",
        weight = 60.0,
        height = 1.65,
        period = "bulking"
    ),
    User(
        id = "5",
        image = R.drawable.ic_profile_placeholder,
        name = "Veni",
        email = "venifeitosa@ufc.alu.br",
        password = "senha123",
        weight = 80.0,
        height = 1.80,
        period = "bulking",
    ),
)
