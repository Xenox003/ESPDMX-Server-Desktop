package de.jxdev.espdmx.server.server

import javax.jmdns.JmDNS
import javax.jmdns.ServiceInfo

class ServiceDiscovery {
    fun advertiseSocket () {
        val jmdns = JmDNS.create()
        val serviceInfo = ServiceInfo.create(
            "_espdmx._tcp.",
            "espdmx-device",
            80,
            "ESPDMX Server"
        )
        jmdns.registerService(serviceInfo)
    }
}