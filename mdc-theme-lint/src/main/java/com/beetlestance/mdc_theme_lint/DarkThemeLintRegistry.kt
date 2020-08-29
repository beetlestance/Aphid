package com.beetlestance.mdc_theme_lint

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.detector.api.CURRENT_API
import com.android.tools.lint.detector.api.Issue
import com.beetlestance.mdc_theme_lint.issues.DirectColorDrawableUseIssue
import com.beetlestance.mdc_theme_lint.issues.DirectColorUseIssue
import com.beetlestance.mdc_theme_lint.issues.MissingNightColorIssue

class DarkThemeLintRegistry : IssueRegistry() {

    override val issues: List<Issue>
        get() = listOf(
            DirectColorUseIssue.ISSUE, DirectColorDrawableUseIssue.ISSUE,
            MissingNightColorIssue.ISSUE
        )

    override val api: Int
        get() = CURRENT_API
}
