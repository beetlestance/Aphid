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
package com.beetlestance.aphid.mdc.theme.lint.issues

import com.android.tools.lint.detector.api.Category
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.Scope
import com.android.tools.lint.detector.api.Severity
import com.beetlestance.aphid.mdc.theme.lint.detectors.MissingNightColorDetector

@Suppress("UnstableApiUsage")
object MissingNightColorIssue {
    private const val ID = "MissingNightColor"
    private const val DESCRIPTION = "Night Color missing"
    const val EXPLANATION: String =
        "Night color value for this color resource seems to be missing. If your app supports dark theme, then you should add an equivalent color resource for it in the night values folder."
    private val CATEGORY = Category.CORRECTNESS
    private const val PRIORITY = 6
    private val SEVERITY = Severity.WARNING

    val ISSUE: Issue = Issue.create(
        ID,
        DESCRIPTION,
        EXPLANATION,
        CATEGORY,
        PRIORITY,
        SEVERITY,
        Implementation(
            MissingNightColorDetector::class.java,
            Scope.RESOURCE_FILE_SCOPE
        )
    )
}
