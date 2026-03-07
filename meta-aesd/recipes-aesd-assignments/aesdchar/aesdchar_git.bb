LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SUMMARY = "AESD character driver"
PV = "1.0+git${SRCPV}"

SRC_URI = "git://github.com/cu-ecen-aeld/assignments-3-and-later-its-me-piyush.git;protocol=https;branch=main"

SRCREV = "5a3a1f7a92b646822d39f53f42f9a8d45bf1dd16"

S = "${WORKDIR}/git/aesd-char-driver"

inherit module update-rc.d

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME = "aesdchar"
INITSCRIPT_PARAMS = "start 98 5 . stop 21 0 1 6 ."

FILES:${PN} += " \
    ${sysconfdir}/init.d/aesdchar \
"

do_install:append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${S}/aesdchar-start-stop ${D}${sysconfdir}/init.d/aesdchar
}
