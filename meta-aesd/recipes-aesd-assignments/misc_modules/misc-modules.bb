inherit module update-rc.d

SUMMARY = "AESD misc kernel modules (hello, faulty)"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "git://github.com/cu-ecen-aeld/assignment-7-its-me-piyush.git;protocol=https;branch=main \
           file://misc-modules-start-stop \
          "
SRCREV = "1b4e73bd1cb2b42fa8068dc2af734225da4b47ae"
PV = "1.0+git${SRCPV}"

S = "${WORKDIR}/git"

EXTRA_OEMAKE += "-C ${STAGING_KERNEL_DIR} M=${S}/misc-modules EXTRA_CFLAGS=-I${S}/include"

INITSCRIPT_NAME = "misc-modules-start-stop"
INITSCRIPT_PARAMS = "defaults 97"
INITSCRIPT_PACKAGES = "${PN}"

FILES:${PN} += "${sysconfdir}/init.d/misc-modules-start-stop ${sysconfdir}/init.d"
RDEPENDS:${PN} += "kmod kernel-module-hello kernel-module-faulty"

do_install:append() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/misc-modules-start-stop ${D}${sysconfdir}/init.d/misc-modules-start-stop
}
