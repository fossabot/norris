package io.dotanuki.norris.search

import android.view.LayoutInflater
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ChipsGroupPopulator(
    private val group: ChipGroup,
    private val chipLayout: Int
) {

    private val inflater by lazy {
        LayoutInflater.from(group.context)
    }

    fun populate(entries: List<String>, onChipClicked: (String) -> Unit) {

        entries.forEach { entry ->
            val chip = inflater.inflate(chipLayout, null) as Chip
            group.addView(
                chip.apply {
                    text = entry
                    setOnClickListener { onChipClicked(entry) }
                }
            )
        }
    }
}