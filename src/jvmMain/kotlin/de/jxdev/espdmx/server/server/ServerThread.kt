package de.jxdev.espdmx.server.server

import com.movisens.smartgattlib.*;
import com.movisens.smartgattlib.attributes.*;
import com.movisens.smartgattlib.helper.*;
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class ServerThread : Runnable {

    override fun run() {
        GlobalScope.launch {
            ServiceDiscovery().advertiseSocket()
        }

        val serviceUuid: UUID? = null // service.getUuid();

        if (Services.HEART_RATE.getUuid().equals(serviceUuid)) {

            // TODO: iterate over characteristics
            val characteristicUuid: UUID? = null // characteristic.getUuid();
            if (Characteristics.HEART_RATE_MEASUREMENT.getUuid().equals(characteristicUuid)) {
                // TODO: Enable notification e.g. for Android API 18:
                // BluetoothGattDescriptor descriptor = characteristic.getDescriptor(Descriptor.CLIENT_CHARACTERISTIC_CONFIGURATION);
                // descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                // mBluetoothGatt.writeDescriptor(descriptor);
            }
        } else {
            System.out.println("Found unused Service: " + Services.lookup(serviceUuid))
        }

// onCharacteristicChanged

// onCharacteristicChanged
        val uuid: UUID? = null // characteristic.getUuid();

        val data: ByteArray? = null // characteristic.getValue();


        val a: AbstractAttribute = Characteristics.lookup(uuid).createAttribute(data)
        if (a is HeartRateMeasurement) {
            val heartRateMeasurement = a as HeartRateMeasurement
            heartRateMeasurement.hr
            heartRateMeasurement.ee
        } else if (a is DefaultAttribute) {
            System.err.println("characteristic for $uuid is unknown")
        } else {
            println("unused characteristic " + a.characteristic.name)
        }

// write Attribute

// write Attribute
        val aa: AbstractAttribute = Weight(12.3)
// TODO: Write aa.getBytes() to aa.getCharacteristic().getUuid();

        embeddedServer(Netty, port = 80, module = Application::main).start(wait = true)
    }

}