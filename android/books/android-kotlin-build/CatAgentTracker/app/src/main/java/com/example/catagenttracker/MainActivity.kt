package com.example.catagenttracker

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.catagenttracker.RouteTrackingService.Companion.EXTRA_SECRET_CAT_AGENT_ID
import com.example.catagenttracker.databinding.ActivityMainBinding
import com.example.catagenttracker.worker.CatFurGroomingWorker
import com.example.catagenttracker.worker.CatLitterBoxSittingWorker
import com.example.catagenttracker.worker.CatStretchingWorker
import com.example.catagenttracker.worker.CatSuitUpWorker

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val workManager = WorkManager.getInstance(this)

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ensurePermissionGrantedAndDispatchCat()
        setupObservers()
    }

    private fun dispatchCat() {
        val networkConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val catAgentId = "CatAgent1"

        /*val catStretchingRequest = OneTimeWorkRequest.Builder(CatStretchingWorker::class.java)
            .setConstraints(networkConstraints)
            .setInputData(getCatAgentIdInputData(CatStretchingWorker.INPUT_DATA_CAT_AGENT_ID, catAgentId))
            .build()*/

        val workerConfigs = listOf(
            WorkerConfig(
                CatStretchingWorker::class.java,
                CatStretchingWorker.INPUT_DATA_CAT_AGENT_ID,
                "Agent done stretching"
            ),
            WorkerConfig(
                CatFurGroomingWorker::class.java,
                CatFurGroomingWorker.INPUT_DATA_CAT_AGENT_ID,
                "Agent done grooming its fur"
            ),
            WorkerConfig(
                CatLitterBoxSittingWorker::class.java,
                CatLitterBoxSittingWorker.INPUT_DATA_CAT_AGENT_ID,
                "Agent done sitting in litter box"
            ),
            WorkerConfig(
                CatSuitUpWorker::class.java,
                CatSuitUpWorker.INPUT_DATA_CAT_AGENT_ID,
                "Agent done suiting up. Ready to go!"
            ),
        )

        val workRequests = workerConfigs.map { config ->
            OneTimeWorkRequest.Builder(config.workerClass)
                .setConstraints(networkConstraints)
                .setInputData(getCatAgentIdInputData(config.inputKey, catAgentId))
                .build()
        }

        /*workManager.beginWith(catStretchingRequest)
            .then(catFurGroomingRequest)
            .then(catLitterBoxSittingRequest)
            .then(catSuitUpRequest)
            .enqueue()*/

        if (workRequests.isNotEmpty()) {
            var continuation = workManager.beginWith(workRequests.first())
            for (i in 1 until workRequests.size) {
                continuation = continuation.then(workRequests[i])
            }
            continuation.enqueue()
        }

        /*workManager.getWorkInfoByIdLiveData(catStretchingRequest.id).observe(this) { info ->
            if (info.state.isFinished) {
                showResult("Agent done stretching!")
            }
        }*/

        workRequests.forEachIndexed { index, request ->
            val successMessage = workerConfigs[index].successMessage
            val isLastRequest = index == workRequests.lastIndex

            workManager.getWorkInfoByIdLiveData(request.id).observe(this) { info ->
                if (info?.state?.isFinished == true) {
                    showResult(successMessage)
                    if (isLastRequest) {
                        launchTrackingService()
                    }
                }
            }
        }
    }

    private fun showResult(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun getCatAgentIdInputData(catAgentIdKey: String, catAgentIdValue: String) =
        Data.Builder()
            .putString(catAgentIdKey, catAgentIdValue)
            .build()

    private fun setupObservers() {
        RouteTrackingService.trackingCompletion.observe(this) { agentId ->
            showResult("Agent $agentId arrived!")
        }
    }

    private fun launchTrackingService() {
        val serviceIntent = Intent(this, RouteTrackingService::class.java).apply {
            putExtra(EXTRA_SECRET_CAT_AGENT_ID, "007")
        }
        ContextCompat.startForegroundService(this, serviceIntent)
    }

    private fun ensurePermissionGrantedAndDispatchCat() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            dispatchCat()
            return
        }

        requestPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                dispatchCat()
            } else {
                showPermissionRationale {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        }

        when {
            checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED -> {
                dispatchCat()
            }

            shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) -> {
                showPermissionRationale {
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }

            else -> requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun showPermissionRationale(positiveAction: () -> Unit) {
        AlertDialog.Builder(this)
            .setTitle("Notifications permission")
            .setMessage("To show progress, we need the notifications permission")
            .setPositiveButton(
                android.R.string.ok
            ) { _, _ -> positiveAction() }
            .setNegativeButton(
                android.R.string.cancel
            ) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    private data class WorkerConfig<T : ListenableWorker>(
        val workerClass: Class<T>,
        val inputKey: String,
        val successMessage: String
    )

    private fun <T : ListenableWorker> createWorkRequest(
        workerClass: Class<T>,
        inputKey: String,
        inputValue: String,
        constraints: Constraints
    ): OneTimeWorkRequest {
        return OneTimeWorkRequest.Builder(workerClass)
            .setConstraints(constraints)
            .setInputData(getCatAgentIdInputData(inputKey, inputValue))
            .build()
    }

    private fun observeWorkResult(request: OneTimeWorkRequest, message: String) {
        workManager.getWorkInfoByIdLiveData(request.id).observe(this) { info ->
            if (info?.state?.isFinished == true) {
                showResult(message)
            }
        }
    }
}