# aesd-assignments_git.bb
# Builds aesdsocket (Assignment 3) and autostarts it on BusyBox/SysV using update-rc.d

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SUMMARY = "AESD assignments - aesdsocket"
PV = "1.0+git${SRCPV}"

# CI-safe suggestion: use protocol=https (SSH needs keys).
# If you insist on SSH, keep yours, but CI may fail without credentials.
SRC_URI = "git://github.com/cu-ecen-aeld/assignments-3-and-later-its-me-piyush.git;protocol=https;branch=main"

SRCREV = "8282ae3d95e069c22095bac6ed6600d2dc39dad7"

# Assignment 3 aesdsocket lives here
S = "${WORKDIR}/git/server"

FILES:${PN} += " \
    ${bindir}/aesdsocket \
    ${sysconfdir}/init.d/aesdsocket \
"

TARGET_LDFLAGS:append = " -pthread -lrt"

inherit update-rc.d

INITSCRIPT_PACKAGES = "${PN}"

# Name of the installed init script in /etc/init.d/
INITSCRIPT_NAME = "aesdsocket"

# NOTE: you had a typo: INITSCRIPT_PRAMS -> INITSCRIPT_PARAMS
INITSCRIPT_PARAMS = "defaults 99"

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

    # Install init script (from your repo) into /etc/init.d/aesdsocket
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/aesdsocket-start-stop ${D}${sysconfdir}/init.d/aesdsocket
}
