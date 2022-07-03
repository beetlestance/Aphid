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
package com.beetlestance.aphid.mdc.theme.lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue
import com.beetlestance.aphid.mdc.theme.lint.issues.DirectColorDrawableUseIssue
import com.beetlestance.aphid.mdc.theme.lint.issues.DirectColorUseIssue
import com.beetlestance.aphid.mdc.theme.lint.issues.MissingNightColorIssue

@Suppress("UnstableApiUsage", "UnstableTypeUsedInSignature")
class DarkThemeLintRegistry : IssueRegistry() {


    override val issues: List<Issue>
        get() = listOf(
            DirectColorUseIssue.ISSUE,
            DirectColorDrawableUseIssue.ISSUE,
            MissingNightColorIssue.ISSUE
        )

    override val api: Int
        get() = CURRENT_API
}
