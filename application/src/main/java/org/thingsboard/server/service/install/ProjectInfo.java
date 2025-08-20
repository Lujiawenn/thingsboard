/**
 * Copyright © 2016-2025 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.server.service.install;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
@Slf4j
public class ProjectInfo {

    private final BuildProperties buildProperties;

    @Autowired
    public ProjectInfo(@Nullable BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    public String getProjectVersion() {
        if (buildProperties != null) {
            return buildProperties.getVersion().replaceAll("[^\\d.]", "");
        } else {
            log.warn("BuildProperties bean is not available, using default version");
            return getDefaultVersion();
        }
    }

    public String getProductType() {
        return "CE";
    }

    private String getDefaultVersion() {
        // 使用固定的默认版本号，避免从配置文件中读取未处理的占位符
        // 根据项目版本4.2.0-SNAPSHOT，使用4.2.0作为默认版本
        return "4.2.0";
    }

}
