# Copyright (c) 2012-2013 LG Electronics, Inc.

SUMMARY = "Open webOS shutdown scripts"
SECTION = "webos/base"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

RDEPENDS_${PN} = "fbprogress"

PV = "2.1.0-36+git${SRCPV}"
SRCREV = "9d4d6a73e7f4bc33fa5cee21274400a23485784f"

inherit webos_public_repo
inherit webos_cmake
inherit allarch

# Depends on MACHINE_ARCH recipe fbprogress as detected by bitbake-diffsigs
# Hash for dependent task fbprogress.bb.do_packagedata changed from 0f207eedf6f0279c8f8351d7e2067b9d to 85c28e7ff4dd0c99f301c4f61052ff89
PACKAGE_ARCH = "${MACHINE_ARCH}"

SRC_URI = "${OPENWEBOS_GIT_REPO_COMPLETE}"
S = "${WORKDIR}/git"
