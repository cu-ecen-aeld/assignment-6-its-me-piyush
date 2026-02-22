# aesd-assignments_git.bb
# Builds aesdsocket and autostarts it on BusyBox/SysV without update-rc.d (no postinstall failures)

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SUMMARY = "AESD assignments - aesdsocket"
PV = "1.0+git${SRCPV}"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignments-3-and-later-its-me-piyush.git;protocol=ssh;branch=main \
           file://aesdsocket-start-stop \
          "

SRCREV = "8282ae3d95e069c22095bac6ed6600d2dc39dad7"

S = "${WORKDIR}/git/server"

FILES:${PN} += " \
    ${bindir}/aesdsocket \
    ${sysconfdir}/init.d/aesdsocket \
    ${sysconfdir}/rcS.d/S99aesdsocket \
"

TARGET_LDFLAGS:append = " -pthread -lrt"

do_configure() {
    :
}

do_compile() {
    oe_runmake
}

do_install() {
    # Install aesdsocket binary
    install -d ${D}${bindir}
    install -m 0755 ${S}/aesdsocket ${D}${bindir}/aesdsocket

    # Install the ONE script as the init script
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/aesdsocket-start-stop ${D}${sysconfdir}/init.d/aesdsocket

    # Ensure it runs at boot: link into rcS.d
    install -d ${D}${sysconfdir}/rcS.d
    ln -sf ../init.d/aesdsocket ${D}${sysconfdir}/rcS.d/S99aesdsocket
}
