SUMMARY = "Device Tree Lopper"
DESCRIPTION = "Tool for manipulation of system device tree files"
LICENSE = "BSD-3-Clause"
SECTION = "bootloader"

FILESEXTRAPATHS:append := ":${THISDIR}/lopper"

SRC_URI = "git://github.com/devicetree-org/lopper.git;branch=master;protocol=https"
SRCREV = "f70eb86385f49545070a84ad756902b3cd607e21"
S = "${WORKDIR}/git"

PV="v1.0.2+git${SRCPV}"

LIC_FILES_CHKSUM = "file://LICENSE.md;md5=8e5f5f691f01c9fdfa7a7f2d535be619"

RDEPENDS:${PN} = " \
    python3-core \
    python3-dtc \
    python3-humanfriendly \
    python3-ruamel-yaml \
    python3-anytree \
    python3-six \
    python3-pyyaml \
    "

inherit setuptools3

INHIBIT_PACKAGE_STRIP = "1"

do_install() {
        distutils3_do_install

        # we have to remove the vendor'd libfdt, since an attempt to strip it
        # will be made, and it will fail in a cross environment.
        rm -rf ${D}/${PYTHON_SITEPACKAGES_DIR}/${BPN}/vendor
}

BBCLASSEXTEND = "native nativesdk"

python() {
    d.delVarFlag('do_configure', 'noexec')
    d.delVarFlag('do_compile', 'noexec')
}
