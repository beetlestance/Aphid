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
package com.beetlestance.aphid.mdc_theme_lint.detectors

import com.android.resources.ResourceFolderType
import com.android.tools.lint.detector.api.Context
import com.android.tools.lint.detector.api.Location
import com.android.tools.lint.detector.api.ResourceXmlDetector
import com.android.tools.lint.detector.api.XmlContext
import com.beetlestance.aphid.mdc_theme_lint.issues.MissingNightColorIssue
import org.w3c.dom.Element

private const val COLOR = "color"

@Suppress("UnstableApiUsage", "UnstableTypeUsedInSignature")
class MissingNightColorDetector : ResourceXmlDetector() {

    private val nightModeColors = mutableListOf<String>()
    private val regularColors = mutableMapOf<String, Location>()

    override fun appliesTo(folderType: ResourceFolderType): Boolean {
        return folderType == ResourceFolderType.VALUES
    }

    override fun getApplicableElements(): Collection<String>? {
        return listOf(COLOR)
    }

    override fun afterCheckEachProject(context: Context) {
        regularColors.forEach { (color, location) ->
            if (!nightModeColors.contains(color))
                context.report(
                    MissingNightColorIssue.ISSUE,
                    location,
                    MissingNightColorIssue.EXPLANATION
                )
        }
    }

    override fun visitElement(context: XmlContext, element: Element) {
        if ((context.getFolderConfiguration() ?: return).isDefault)
            regularColors[element.getAttribute("name")] = context.getLocation(element)
        else if ((context.getFolderConfiguration() ?: return).nightModeQualifier.isValid)
            nightModeColors.add(element.getAttribute("name"))
    }
}
