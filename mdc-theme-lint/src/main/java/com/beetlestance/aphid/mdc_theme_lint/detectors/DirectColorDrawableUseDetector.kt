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
import com.android.tools.lint.detector.api.ResourceXmlDetector
import com.android.tools.lint.detector.api.XmlContext
import com.beetlestance.aphid.mdc_theme_lint.issues.DirectColorDrawableUseIssue
import org.w3c.dom.Attr

// Vector Drawables.
private const val FILL_COLOR = "fillColor"
private const val STROKE_COLOR = "strokeColor"
private const val COLOR = "color"
private const val TINT = "tint"

@Suppress("UnstableApiUsage", "UnstableTypeUsedInSignature")
class DirectColorDrawableUseDetector : ResourceXmlDetector() {

    override fun appliesTo(folderType: ResourceFolderType): Boolean {
        return folderType == ResourceFolderType.DRAWABLE || folderType == ResourceFolderType.COLOR
    }

    override fun getApplicableAttributes(): Collection<String> {
        return listOf(TINT, FILL_COLOR, STROKE_COLOR, COLOR)
    }

    override fun visitAttribute(context: XmlContext, attribute: Attr) {
        if (attribute.value.startsWith("#")) {
            context.report(
                DirectColorDrawableUseIssue.ISSUE,
                context.getLocation(attribute),
                DirectColorDrawableUseIssue.EXPLANATION
            )
        }
    }
}
