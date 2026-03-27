package com.github.minetoblend.ideasearcheverywherefixer.services

import com.intellij.ide.actions.searcheverywhere.SearchEverywhereFoundElementInfo
import com.intellij.ide.actions.searcheverywhere.SearchEverywhereReorderingService

@Suppress("UnstableApiUsage")
class SearchEverywhereFixer : SearchEverywhereReorderingService {
    override fun isEnabled() = true

    override fun isEnabledInTab(tabID: String) = true

    override fun reorder(
        tabID: String,
        items: MutableList<SearchEverywhereFoundElementInfo>
    ) {
        items.sortWith(
            compareBy<SearchEverywhereFoundElementInfo> { item ->
                when (item.contributor.searchProviderId) {
                    "ClassSearchEverywhereContributor" -> 0
                    else -> 1
                }
            }.thenByDescending { it.priority }
        )
    }
}