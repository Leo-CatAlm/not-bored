# not-bored
not-bored is an app that suggest tons of activities to the user.
The user just have to give the number of participants and the type of activity that they want to do (optional)

## usage
The user install the app and follow the given steps:
```markdown
 #First screen
 
    ·Read the terms and conditions
    ·Input the number of participants
    ·Press the start button
    
 #Second screen
 
    ·Select the type of activity or press the random button
    
 #Third screen
 
    ·Do the given activity or
    ·Press the 'Try Another' button to retrieve another activity
```

# About the code

```kotlin
 //JSON model
  data class ActivitiesResponse (
        val activity: String="",
        val type: String="Nothing",
        val participants: Int=0,
        val price: Double=0.0,
        val error: String="Nothing"
        ): Serializable
 
 //Retrofit Interface
 interface ActivitiesService {

    /**
     * It retrieves a random activity to the user, only asking for the number of participants
     * @param participants : the number of participants
     */
    @GET("api/activity")
    suspend fun getRandomActivity(@Query("participants") participants: Int): Response<ActivitiesResponse>

    /**
     * It retrieves an activity considering the subject and the number of participants
     * @param participants : the number of participants
     * @param type : the type of the activity
     */
    @GET("api/activity")
    suspend fun getActivity(@Query("type") activity: String, @Query("participants") participants: Int): Response<ActivitiesResponse>
}

 //Retrofit Client
 object RetroFitClient {

      const val BASE_URL = "http://www.boredapi.com/"

    /**
     * Executes a call from the app to boredapi.
     * By using the methods in the retrofit interface to choose between a random activity type, and a given activity type
     * @param url : the base url from where we'll get the activities
     */
        fun getInstance(url: String) : ActivitiesService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ActivitiesService::class.java)
        }

}

 //Main activity funs
 /**
     * Set the listeners of the start button and the terms hyperText.
     *  - The start buttons retrieve messages in case the user don't give a number of participants or gives it with a wrong format
     *  - The terms hyperText send the user to a screen with everything that the user should accept to continue
     */
    fun setListeners(){
        binding.btnStart.setOnClickListener {

            val numberParticipants=binding.etNumber.text
            when{
                numberParticipants.isNullOrEmpty()-> Toast.makeText(baseContext,R.string.empty_participants,Toast.LENGTH_LONG).show()
                !isInteger(numberParticipants.toString())-> Toast.makeText(this,R.string.type_participants,Toast.LENGTH_LONG).show()
                else-> {
                    Toast.makeText(this,R.string.loading,Toast.LENGTH_LONG).show()
                    val goDetail= Intent(this, TypesActivity::class.java)
                    goDetail.putExtra("participants",numberParticipants.toString().toInt())
                    startActivity(goDetail)
                }
            }

        }

        binding.tvTerm.setOnClickListener {
            val goTerm= Intent(this, TermActivity::class.java)
            startActivity(goTerm)

            //Ir a la otra actividad
        }

    }

    /**
     * This fun allows us to control if the user put a number or not
     * @param s : This is what the user write in the participants bar
     */
    fun isInteger(s: String): Boolean {
        return try {
            s.toInt()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
    
 //TypesActivity funs
  /**
     * This fun suggest a random activity (as the name says). It receives the number of participants that were given in the previous screen
     * With the Activity that was retrieved the app will send the user to the next screen to see the details
     * @param participants : the number of participants
     */
    fun suggestRandomActivity(participants: Int){
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetroFitClient.getInstance(RetroFitClient.BASE_URL).getRandomActivity(participants)
            val activity: ActivitiesResponse? = call.body()
            if (call.isSuccessful){
                val goDetail = Intent(this@TypesActivity, ActivityDetail::class.java)
                goDetail.putExtra("activityType", activity)
                println("Activity $activity")

                startActivity(goDetail)
            } else {
                Log.e("Call", call.errorBody().toString())
            }
        }
    }

    /**
     * This fun suggest a given activity (as the name says). It receives the number of participants that were
     * given in the previous screen, and the type of activity given in the current screen.
     * With the Activity that was retrieved the app will send the user to the next screen to see the details
     * @param participants : the number of participants
     * @param type :  the type of activity that will be suggested
     */
    private fun suggestGivenActivity(type: String, participants: Int){
        println("type $type $participants")
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetroFitClient.getInstance(RetroFitClient.BASE_URL).getActivity(type, participants)
            val activity: ActivitiesResponse? = call.body()
            if (call.isSuccessful){
                val goDetail = Intent(this@TypesActivity, ActivityDetail::class.java)
                goDetail.putExtra("activityType", activity)
                println("Activity $activity")

                startActivity(goDetail)
            } else {
                Log.e("Call", call.errorBody().toString())
            }
        }
    }
    
 //Activity Detail funs
 /**
     * This fun allows the user to get a new activity with the preview parameters that were used to get
     * the first activity
     * @param participants : the number of participants
     */
   private fun refreshActivity(participants: Int){
     CoroutineScope(Dispatchers.IO).launch{
         val call = RetroFitClient.getInstance(RetroFitClient.BASE_URL).getRandomActivity(participants)
         val activities: ActivitiesResponse? = call.body()
         runOnUiThread {
             if (call.isSuccessful){
                 with(binding){

                         tvActivityType.text = activities.type
                         tvActivityTitle.text = activities.activity
                         tvNumberOfParticipants.text = activities.participants.toString()

                 }
             }else{
                 if (activities != null) {
                     Toast.makeText(this@ActivityDetail,activities.error,Toast.LENGTH_LONG).show()
                 }
                 }
             }
         }

     }
```

# Contributors

[Stefano](https://github.com/Stefanodepetriss)

[Luciano](https://github.com/CassaniMeli2022)

[Leonardo](https://github.com/Leo-CatAlm)
