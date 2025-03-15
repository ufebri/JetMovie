package com.raylabs.jetmovie.util

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import com.raylabs.jetmovie.utils.PermissionManager
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PermissionManagerTest {

    private lateinit var permissionManager: PermissionManager
    private lateinit var activity: ComponentActivity

    @Before
    fun setup() {
        activity = Mockito.mock(ComponentActivity::class.java) // Mock ComponentActivity
        permissionManager = PermissionManager(activity)
    }

    @Test
    fun `isPermissionNotificationGranted should return true when permission is granted`() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Mockito.mockStatic(ContextCompat::class.java).use { contextMock ->
                contextMock.`when`<Int> {
                    ContextCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS)
                }.thenReturn(PackageManager.PERMISSION_GRANTED)

                val result = permissionManager.isPermissionNotificationGranted()
                assertTrue(result) // Harus true karena izin diberikan
            }
        }
    }

    @Test
    fun `isPermissionNotificationGranted should return false when permission is denied`() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Mockito.mockStatic(ContextCompat::class.java).use { contextMock ->
                contextMock.`when`<Int> {
                    ContextCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS)
                }.thenReturn(PackageManager.PERMISSION_DENIED)

                val result = permissionManager.isPermissionNotificationGranted()
                assertFalse(result) // Harus false karena izin ditolak
            }
        }
    }
}
