package com.jeff.imeja.ui.registration

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import ca.uhn.fhir.context.FhirContext
import ca.uhn.fhir.context.FhirVersionEnum
import com.google.android.fhir.datacapture.QuestionnaireFragment
import com.google.android.fhir.datacapture.mapping.ResourceMapper
import com.jeff.imeja.R
import kotlinx.coroutines.launch
import org.hl7.fhir.r4.model.Questionnaire

class RegisterActivity : AppCompatActivity() {
    var questionnaireJsonString: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

// Step 2: Configure a QuestionnaireFragment
        questionnaireJsonString = getStringFromAssets("questionnaire.json")

        val questionnaireFragment =
            QuestionnaireFragment.builder().setQuestionnaire(questionnaireJsonString!!).build()
// Step 3: Add the QuestionnaireFragment to the FragmentContainerView
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragment_container_view, questionnaireFragment)
            }
        }
// Submit button callback
        supportFragmentManager.setFragmentResultListener(
            QuestionnaireFragment.SUBMIT_REQUEST_KEY,
            this,
        ) { _, _ ->
            submitQuestionnaire()
        }


    }

    private fun submitQuestionnaire() =
        lifecycleScope.launch {
            // 5 Replace with code from the codelab to get a questionnaire response.
// Get a questionnaire response
            val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view)
                    as QuestionnaireFragment
            val questionnaireResponse = fragment.getQuestionnaireResponse()

// Print the response to the log
            val jsonParser = FhirContext.forCached(FhirVersionEnum.R4).newJsonParser()
            val questionnaireResponseString =
                jsonParser.encodeResourceToString(questionnaireResponse)
            Log.d("response", questionnaireResponseString)

            // 6 Replace with code from the codelab to extract FHIR resources from QuestionnaireResponse.

            val questionnaire =
                jsonParser.parseResource(questionnaireJsonString) as Questionnaire
            val bundle = ResourceMapper.extract(questionnaire, questionnaireResponse)
            Log.d("extraction result", jsonParser.encodeResourceToString(bundle))

        }

    private fun getStringFromAssets(fileName: String): String {
        return assets.open(fileName).bufferedReader().use { it.readText() }
    }
}