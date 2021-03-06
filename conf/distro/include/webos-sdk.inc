# Copyright (c) 2012-2014 LG Electronics, Inc.

# bitbake.conf weak assigns this to be "oecore" before this file is included.
SDK_NAME_PREFIX = "luneos-sdk"

# bitbake.conf assigns this to be "-oesdk" before this file is included.
SDK_VENDOR = "-luneossdk"

# bitbake.conf assigns this to be under /usr/local; override it to be under /opt .
SDKPATH = "/opt/${SDK_NAME_PREFIX}-${SDK_ARCH}/${SDK_VERSION}"

# default-distrovars.inc weak assigns this after this file is included.
SDK_VERSION ?= "${DISTRO_VERSION}"
