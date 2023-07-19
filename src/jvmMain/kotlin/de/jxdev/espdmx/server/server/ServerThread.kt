package de.jxdev.espdmx.server.server

import com.welie.blessed.BluetoothCentralManager
import com.welie.blessed.BluetoothCentralManagerCallback
import com.welie.blessed.BluetoothPeripheral
import com.welie.blessed.ScanResult
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class ServerThread : Runnable {

    private val bluetoothCentralManagerCallback: BluetoothCentralManagerCallback =
        object : BluetoothCentralManagerCallback() {
            override fun onDiscoveredPeripheral(peripheral: BluetoothPeripheral, scanResult: ScanResult) {
                print(peripheral.toString())
                central.stopScan()
                //central.connectPeripheral(peripheral, peripheralCallback)
            }
        }

// Create BluetoothCentralManager

    // Create BluetoothCentralManager
    var central = BluetoothCentralManager(bluetoothCentralManagerCallback)

    override fun run() {
        GlobalScope.launch {
            ServiceDiscovery().advertiseSocket()
        }

        central.scanForPeripherals()

        embeddedServer(Netty, port = 80, module = Application::main).start(wait = true)
    }

}