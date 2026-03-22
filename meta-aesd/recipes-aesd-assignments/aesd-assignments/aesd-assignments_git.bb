LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SUMMARY = "AESD assignments - aesdsocket"
PV = "1.0+git${SRCPV}"

SRC_URI = "git://github.com/cu-ecen-aeld/assignments-3-and-later-its-me-piyush.git;protocol=https;branch=main"

SRCREV = "85a5dbbe502b33080c8a0221d94638f841644832"

S = "${WORKDIR}/git/server"

FILES:${PN} += " \
    ${bindir}/aesdsocket \
    ${sysconfdir}/init.d/aesdsocket \
"

TARGET_LDFLAGS:append = " -pthread -lrt"

inherit update-rc.d

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME = "aesdsocket"
INITSCRIPT_PARAMS = "start 99 5 . stop 20 0 1 6 ."

do_configure() {
    :
}

do_compile() {
    oe_runmake
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/aesdsocket ${D}${bindir}/aesdsocket

    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d/aesdsocket
}
