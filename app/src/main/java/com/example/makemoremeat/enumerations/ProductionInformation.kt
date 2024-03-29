package com.example.makemoremeat.enumerations

import com.example.makemoremeat.R

enum class ProductionInformation(
    val initialNumber: Double,
    val initialCost: Double,
    val initialProduction: Double,
    val initialProductionTime: Double,
    val coefficientCostUp: Double,
    val imageProduction: Int
) {

    Chicken(1.0, 1.0, 0.1, 1000.0, 1.13, R.drawable.chicken),
    Beef(0.0, 7.0, 0.7, 2000.0, 1.14, R.drawable.production_work_in_progress),
    Mutton(0.0, 49.0, 4.9, 4000.0, 1.15, R.drawable.production_work_in_progress),
    Pork(0.0, 343.0, 34.3, 8000.0, 1.16, R.drawable.production_work_in_progress),
    Rabbit(0.0, 2401.0, 240.1, 16000.0, 1.17, R.drawable.production_work_in_progress),
    Horse(0.0, 16807.0, 1680.7, 32000.0, 1.18, R.drawable.production_work_in_progress),
    Caribou(0.0, 117650.0, 11765.0, 64000.0, 1.19, R.drawable.production_work_in_progress),
    Fish(0.0, 823540.0, 82354.0, 128000.0, 1.20, R.drawable.production_work_in_progress),
    Dog(0.0, 5764800.0, 576480.0, 256000.0, 1.21, R.drawable.production_work_in_progress),
    Elephant(0.0, 40354000.0, 4035400.0, 512000.0, 1.22, R.drawable.production_work_in_progress),
    Bugs(0.0, 282480000.0, 28248000.0, 1024000.0, 1.23, R.drawable.production_work_in_progress),
    Vegan(0.0, 1977300000.0, 197730000.0, 2048000.01, 1.24, R.drawable.production_work_in_progress),
    Cultured(
        0.0,
        13841000000.0,
        1384100000.0,
        4096000.0,
        1.25,
        R.drawable.production_work_in_progress
    )
}