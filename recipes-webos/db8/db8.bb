# Copyright (c) 2013-2014 LG Electronics, Inc.

SUMMARY = "A userspace service that provides access to the Open webOS database"
SECTION = "webos/base"
AUTHOR = "Maksym Sditanov <maxim.sditanov@lge.com>"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# db8 is also the provider for mojodb
PROVIDES = "mojodb"

DEPENDS = "luna-service2 jemalloc icu pmloglib curl glib-2.0 leveldb leveldb-tl boost"

WEBOS_VERSION = "3.2.0-142_ab66a36429b97a62ac06c086674bd4f9d13460c3"

# ensure leveldb is installed in image
RDEPENDS_${PN} = "leveldb"

inherit webos_component
inherit webos_public_repo
inherit webos_enhanced_submissions
inherit webos_cmake
inherit webos_system_bus
inherit webos_daemon
inherit webos_library
inherit webos_system_bus

EXTRA_OECMAKE += "-DWEBOS_CONFIG_BUILD_TESTS:BOOL=TRUE -DWEBOS_DB8_BACKEND:STRING='leveldb;sandwich' -DCMAKE_SKIP_RPATH:BOOL=TRUE"

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"

PACKAGES =+ "${PN}-tests"

FILES_${PN}-tests = "${libdir}/${PN}/tests/*"
FILES_${PN}-dbg += "${libdir}/${PN}/tests/.debug"

SRC_URI += "file://0001-Add-com.palm.mediapermissions-service-as-admin-for-m.patch"
