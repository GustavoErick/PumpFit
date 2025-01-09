//package com.example.pumpfit.model.mock
//
//import com.example.pumpfit.R
//import com.example.pumpfit.model.Exercise
//
//val mockExercises = listOf(
//    Exercise(
//        id = "1",
//        name = "Rosca Direta",
//        description = "Exercício para bíceps utilizando barra.",
//        muscleGroup = "Braço",
//        weight = "30kg",
//        sets = "3x15-12-10",
//        methodology = "Tensional/Corda",
//        interval = "01:55",
//        image = R.drawable.placeholder_image,
//        video = R.raw.placeholder_video,
//        machines = listOf("Barra Reta", "Halteres", "Máquina de Rosca")
//    ),
//    Exercise(
//        id = "2",
//        name = "Supino Reto",
//        description = "Exercício para peitoral realizado no banco reto.",
//        muscleGroup = "Peito",
//        weight = "40kg",
//        sets = "4x12-10-8-6",
//        methodology = "Progressivo",
//        interval = "02:00",
//        image = R.drawable.placeholder_image,
//        video = R.raw.placeholder_video,
//        machines = listOf("Banco Reto", "Halteres", "Barra Olímpica")
//    )
//)

package com.example.pumpfit.model.mock

import com.example.pumpfit.R
import com.example.pumpfit.model.Exercise

val mockExercises = listOf(
    // Exercícios para Braço
    Exercise(
        id = "1",
        name = "Rosca Direta",
        description = "Exercício para bíceps utilizando barra.",
        muscleGroup = "Braço",
        weight = "30kg",
        sets = "3x15-12-10",
        methodology = "Tensional/Corda",
        interval = "01:55",
        image = R.drawable.rosca_direta_image,
        video = R.raw.rosca_direta_video,
        machines = listOf("Barra Reta", "Halteres", "Máquina de Rosca")
    ),
    Exercise(
        id = "2",
        name = "Tríceps Testa",
        description = "Exercício para tríceps realizado com barra ou halteres.",
        muscleGroup = "Braço",
        weight = "20kg",
        sets = "4x12",
        methodology = "Controle Total",
        interval = "01:30",
        image = R.drawable.triceps_testa_image,
        video = R.raw.triceps_testa_video,
        machines = listOf("Barra EZ", "Máquina de Tríceps", "Halteres")
    ),
    Exercise(
        id = "3",
        name = "Rosca Concentrada",
        description = "Exercício isolado para bíceps.",
        muscleGroup = "Braço",
        weight = "12kg",
        sets = "3x10",
        methodology = "Foco Isolado",
        interval = "01:20",
        image = R.drawable.rosca_concentrada_image,
        video = R.raw.rosca_direta_video,
        machines = listOf("Halteres", "Banco Ajustável")
    ),

    // Exercícios para Peito
    Exercise(
        id = "4",
        name = "Supino Reto",
        description = "Exercício para peitoral realizado no banco reto.",
        muscleGroup = "Peito",
        weight = "40kg",
        sets = "4x12-10-8-6",
        methodology = "Progressivo",
        interval = "02:00",
        image = R.drawable.supino_reto_image,
        video = R.raw.supino_reto_video,
        machines = listOf("Banco Reto", "Halteres", "Barra Olímpica")
    ),
    Exercise(
        id = "5",
        name = "Crucifixo Inclinado",
        description = "Exercício para peitoral superior utilizando halteres.",
        muscleGroup = "Peito",
        weight = "15kg",
        sets = "3x12",
        methodology = "Amplitude Máxima",
        interval = "01:45",
        image = R.drawable.crucifixo_inclinado_image,
        video = R.raw.supino_reto_video,
        machines = listOf("Banco Inclinado", "Halteres")
    ),
    Exercise(
        id = "6",
        name = "Crossover",
        description = "Exercício para peitoral realizado em cabos.",
        muscleGroup = "Peito",
        weight = "20kg",
        sets = "4x15",
        methodology = "Resistência Contínua",
        interval = "01:40",
        image = R.drawable.crossover_image,
        video = R.raw.supino_reto_video,
        machines = listOf("Crossover", "Polia")
    ),

    // Exercícios para Ombro
    Exercise(
        id = "7",
        name = "Elevação Lateral",
        description = "Exercício para deltoides laterais.",
        muscleGroup = "Ombro",
        weight = "8kg",
        sets = "3x12",
        methodology = "Controle Total",
        interval = "01:30",
        image = R.drawable.elevacao_lateral_image,
        video = R.raw.elevacao_lateral_video,
        machines = listOf("Halteres", "Máquina de Elevação Lateral")
    ),
    Exercise(
        id = "8",
        name = "Desenvolvimento com Halteres",
        description = "Exercício para ombros realizado com halteres.",
        muscleGroup = "Ombro",
        weight = "25kg",
        sets = "3x10",
        methodology = "Foco no Movimento",
        interval = "01:40",
        image = R.drawable.desenvolvimento_com_halteres_image,
        video = R.raw.desenvolvimento_com_halteres_video,
        machines = listOf("Halteres", "Banco Ajustável")
    ),
    Exercise(
        id = "9",
        name = "Elevação Frontal",
        description = "Exercício para deltoides anteriores.",
        muscleGroup = "Ombro",
        weight = "10kg",
        sets = "4x12",
        methodology = "Amplitude Controlada",
        interval = "01:35",
        image = R.drawable.elevacao_frontal_image,
        video = R.raw.elevacao_frontal_video,
        machines = listOf("Halteres", "Polia Baixa")
    ),

    // Exercícios para Perna
    Exercise(
        id = "10",
        name = "Agachamento Livre",
        description = "Exercício composto para pernas e glúteos.",
        muscleGroup = "Perna",
        weight = "60kg",
        sets = "4x10",
        methodology = "Movimento Completo",
        interval = "02:00",
        image = R.drawable.agachamento_livre_image,
        video = R.raw.leg_press_video,
        machines = listOf("Barra Olímpica", "Plataforma")
    ),
    Exercise(
        id = "11",
        name = "Leg Press",
        description = "Exercício para quadríceps realizado na máquina.",
        muscleGroup = "Perna",
        weight = "120kg",
        sets = "4x15",
        methodology = "Controle de Carga",
        interval = "01:45",
        image = R.drawable.leg_press_image,
        video = R.raw.leg_press_video,
        machines = listOf("Máquina Leg Press")
    ),
    Exercise(
        id = "12",
        name = "Cadeira Extensora",
        description = "Exercício para isolamento do quadríceps.",
        muscleGroup = "Perna",
        weight = "30kg",
        sets = "4x12",
        methodology = "Isolamento Muscular",
        interval = "01:30",
        image = R.drawable.cadeira_extensora_image,
        video = R.raw.leg_press_video,
        machines = listOf("Cadeira Extensora")
    ),

    // Exercícios para Costas
    Exercise(
        id = "13",
        name = "Remada Curvada",
        description = "Exercício para dorsais realizado com barra.",
        muscleGroup = "Costas",
        weight = "50kg",
        sets = "3x12",
        methodology = "Foco no Movimento",
        interval = "01:40",
        image = R.drawable.remada_curvada_image,
        video = R.raw.remada_curvada_video,
        machines = listOf("Barra Olímpica", "Halteres")
    ),
    Exercise(
        id = "14",
        name = "Pull Down",
        description = "Exercício para dorsais realizado na polia.",
        muscleGroup = "Costas",
        weight = "45kg",
        sets = "4x15",
        methodology = "Controle Contínuo",
        interval = "01:30",
        image = R.drawable.pulldown_image,
        video = R.raw.remada_curvada_video,
        machines = listOf("Máquina de Pull Down")
    ),
    Exercise(
        id = "15",
        name = "Deadlift",
        description = "Exercício composto para costas e glúteos.",
        muscleGroup = "Costas",
        weight = "80kg",
        sets = "3x10",
        methodology = "Explosão Controlada",
        interval = "02:00",
        image = R.drawable.deadlift_image,
        video = R.raw.remada_curvada_video,
        machines = listOf("Barra Olímpica")
    ),

    // Exercícios para Abdômen
    Exercise(
        id = "16",
        name = "Abdominal Reto",
        description = "Exercício básico para fortalecimento do abdômen.",
        muscleGroup = "Abdômen",
        weight = "Corporal",
        sets = "4x20",
        methodology = "Movimento Completo",
        interval = "01:00",
        image = R.drawable.abdominal_reto_image,
        video = R.raw.elevacao_frontal_video,
        machines = listOf("Colchonete")
    ),
    Exercise(
        id = "17",
        name = "Prancha",
        description = "Exercício isométrico para core.",
        muscleGroup = "Abdômen",
        weight = "Corporal",
        sets = "3x30s",
        methodology = "Isometria",
        interval = "01:30",
        image = R.drawable.prancha_image,
        video = R.raw.elevacao_frontal_video,
        machines = listOf("Colchonete")
    ),
    Exercise(
        id = "18",
        name = "Abdominal Obliquo",
        description = "Exercício para oblíquos utilizando rotação.",
        muscleGroup = "Abdômen",
        weight = "Corporal",
        sets = "4x15",
        methodology = "Rotação Controlada",
        interval = "01:20",
        image = R.drawable.abdominal_obliquo_image,
        video = R.raw.elevacao_lateral_video,
        machines = listOf("Colchonete", "Med Ball")
    )
)

