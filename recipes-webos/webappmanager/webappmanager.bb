# Copyright (c) 2010-2013 LG Electronics, Inc.

SUMMARY = "WebAppMgr is responsible for running Enyo applications on webOS"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

DEPENDS = "cjson luna-service2 sqlite3 luna-sysmgr-common luna-sysmgr-ipc luna-sysmgr-ipc-messages qt4-webos librolegen nyx-lib openssl luna-webkit-api webkit-webos luna-prefs libpbnjson npapi-headers freetype serviceinstaller"

DEPENDS_append = " ${@base_conditional('WEBOS_TARGET_MACHINE_IMPL','device','qt-webos-plugin','',d)}"

# webappmgr's upstart conf expects to be able to LD_PRELOAD ptmalloc3
RDEPENDS_${PN} = "ptmalloc3"
# webappmgr's upstart conf expects to have ionice available. Under OE-core, this is supplied by util-linux.
RDEPENDS_${PN} += "util-linux"
#  webappmgr's upstart conf expects setcpushares-task to be available
VIRTUAL-RUNTIME_cpushareholder ?= "cpushareholder-stub"
RDEPENDS_${PN} += "${VIRTUAL-RUNTIME_cpushareholder}"

WEBOS_VERSION = "3.0.0-3_9305987f31cc2f84f99e2bd442c2bf2a330fb961"

RDEPENDS_${PN}_append = " ${@base_conditional('WEBOS_TARGET_MACHINE_IMPL','device','qt-webos-plugin','',d)}"

# Don't uncomment until all of the do_*() tasks have been moved out of the recipe
#inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_qmake
inherit webos_system_bus
inherit webos_machine_dep

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

inherit webos-ports-submissions
SRCREV = "811afcf9ba31f7c058a49fa29ed3087c51ebc3a1"

EXTRA_OEMAKE += "MACHINE=${MACHINE}"

do_configure() {
    MACHINE=${MACHINE} ${QMAKE}
}

do_install() {
    oe_runmake install
    install -d ${D}${bindir}
    install -v -m 750 release-${MACHINE}/WebAppMgr ${D}${bindir}

    install -d ${D}${webos_upstartconfdir}
    install -v -m 644 WebAppMgr.upstart ${D}${webos_upstartconfdir}/WebAppMgr
}

FILES_${PN} += "${webos_upstartconfdir}"

# /usr/bin/WebAppMgr contains RPATH pointing to sysroot
INSANE_SKIP_${PN} = "rpaths"
