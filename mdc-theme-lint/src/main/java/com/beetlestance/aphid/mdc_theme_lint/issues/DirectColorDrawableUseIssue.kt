/*
 * Copyright 2020 BeetleStance
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
package com.beetlestance.aphid.mdc_theme_lint.issues

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.beetlestance.aphid.mdc_theme_lint.detectors.DirectColorDrawableUseDetector

@Suppress("UnstableApiUsage")
object DirectColorDrawableUseIssue {
    private const val ID = "DirectColorInDrawableUse"
    private const val DESCRIPTION = "Color used directly"
    const val EXPLANATION: String =
        "Avoid direct use of colors in XML files. This can cause issues with different theme (dark-theme?) support"
    private val CATEGORY = Category.CORRECTNESS
    private const val PRIORITY = 4
    private val SEVERITY = Severity.WARNING

    val ISSUE: Issue = Issue.create(
        ID,
        DESCRIPTION,
        EXPLANATION,
        CATEGORY,
        PRIORITY,
        SEVERITY,
        Implementation(
            DirectColorDrawableUseDetector::class.java,
            Scope.RESOURCE_FILE_SCOPE
        )
    )
}
