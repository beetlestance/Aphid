/*
 * Copyright 2021 BeetleStance
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
package com.beetlestance.aphid.feature.explore

import androidx.compose.runtime.Immutable
import com.beetlestance.aphid.data.entities.Recipe

@Immutable
internal data class ExploreViewState(
    val breakfastRecipes: List<Recipe> = emptyList(),
    val readyInTimeRecipes: List<Recipe> = emptyList(),
    val recentlyViewedRecipes: List<Recipe> = emptyList()
)
