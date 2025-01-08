package com.example.pumpfit.model.mock

import com.example.pumpfit.model.User

val mockUsers = listOf(
    User(
        id = "1",
        name = "João",
        email = "joao@example.com",
        password = "123456",
        weight = 70.0,
        height = 1.75,
        period = "cutting"
    ),
    User(
        id = "2",
        name = "Maria",
        email = "maria@example.com",
        password = "abcdef",
        weight = 60.0,
        height = 1.65,
        period = "maintenance"
    ),
    User(
        id = "3",
        name = "Carlos",
        email = "carlos@example.com",
        password = "senha123",
        weight = 80.0,
        height = 1.80,
        period = "bulking"
    ),
    User(
        id = "4",
        name = "Gustavo Erick",
        email = "gustaerick@example.com",
        password = "senha123",
        weight = 60.0,
        height = 1.65,
        period = "bulking"
    )
)
