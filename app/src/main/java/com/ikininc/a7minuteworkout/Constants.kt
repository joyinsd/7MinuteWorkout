package com.ikininc.a7minuteworkout

class Constants {

    companion object{

        fun defaultExerciseList():ArrayList<ExerciseModel>{
            val exerciseList= ArrayList<ExerciseModel>()
            val jumpingJack = ExerciseModel(1, "Jumping Jack", R.drawable.ic_jumping_jacks,
                    false, false)
            exerciseList.add(jumpingJack)
            val lunge = ExerciseModel(2, "Lunge", R.drawable.ic_lunge,
                false, false)
            exerciseList.add(lunge)

            val plank = ExerciseModel(3, "Plank", R.drawable.ic_plank,
                false, false)
            exerciseList.add(plank)

            val pushUp = ExerciseModel(4, "Push Up", R.drawable.ic_push_up,
                false, false)
            exerciseList.add(pushUp)

            val pushUpRotation = ExerciseModel(5, "Push Up and Rotation",
                R.drawable.ic_push_up_and_rotation,
                false, false)
            exerciseList.add(pushUpRotation)

            val sidePlank = ExerciseModel(6, "Side Plank", R.drawable.ic_side_plank,
                false, false)
            exerciseList.add(sidePlank)

            val squat = ExerciseModel(7, "Squat", R.drawable.ic_squat,
                false, false)
            exerciseList.add(squat)

            val stepUpOntoChair = ExerciseModel(8, "Step up onto Chair", R.drawable.ic_step_up_onto_chair,
                false, false)
            exerciseList.add(stepUpOntoChair)

            val tricepsDipOnChair = ExerciseModel(9, "Triceps Dip On Chair", R.drawable.ic_triceps_dip_on_chair,
                false, false)
            exerciseList.add(tricepsDipOnChair)

            val wallSit = ExerciseModel(10, "Wall Sit", R.drawable.ic_wall_sit,
                false, false)
            exerciseList.add(wallSit)

            return exerciseList
        }

    }
}