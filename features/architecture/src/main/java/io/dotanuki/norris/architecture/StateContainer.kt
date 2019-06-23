package io.dotanuki.norris.architecture

import io.dotanuki.norris.architecture.ViewState.FirstLaunch
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow

interface StateContainer<T> : ViewStateRegistry<T>, ViewStatesEmitter<T> {

    class Unbounded<T> : StateContainer<T> {

        private val broadcaster by lazy {
            ConflatedBroadcastChannel<ViewState<T>>(FirstLaunch)
        }

        override val emissionScope = MainScope()

        override fun observableStates() = broadcaster.asFlow()

        override fun current(): ViewState<T> = broadcaster.value

        override suspend fun store(state: ViewState<T>) {
            broadcaster.send(state)
        }
    }
}