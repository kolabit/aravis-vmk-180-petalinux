SUMMARY = "Xilinx libdfx library"
DESCRIPTION = "Xilinx libdfx Library and headers"

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://LICENSE.md;md5=94aba86aec117f003b958a52f019f1a7"

BRANCH ?= "xlnx_rel_v2022.1"
REPO ?= "git://github.com/Xilinx/libdfx.git;protocol=https"
BRANCHARG = "${@['nobranch=1', 'branch=${BRANCH}'][d.getVar('BRANCH', True) != '']}"
SRC_URI = "${REPO};${BRANCHARG}"
SRCREV = "6d423bbafd9ac14194a6a80f4d276c9024d2defa"

COMPATIBLE_MACHINE = "^$"
COMPATIBLE_MACHINE:zynqmp = "zynqmp"
COMPATIBLE_MACHINE:versal = "versal"

S = "${WORKDIR}/git"

inherit cmake

do_install () {
    install -d ${D}${libdir}
    install -d ${D}${includedir}
    install -d ${D}${bindir}
    oe_libinstall -so -C ${B}/src/ libdfx ${D}${libdir}
    install -m 0644 ${B}/src/libdfx.a ${D}${libdir}
    install -m 0644 ${B}/include/libdfx.h ${D}${includedir}
    install -m 0755 ${B}/apps/dfx_app ${D}${bindir}
}

SOLIBSDEV = ".so"
FILES:${PN} += "${libdir}/libdfx.so ${bindir}/*"
FILES:${PN}-staticdev = "${libdir}/libdfx.a"
FILES:${PN}-dev = "${includedir}"
