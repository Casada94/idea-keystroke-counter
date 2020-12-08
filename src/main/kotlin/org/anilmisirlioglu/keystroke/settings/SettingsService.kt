package org.anilmisirlioglu.keystroke.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import org.anilmisirlioglu.keystroke.settings.Settings.KeyboardFlags
import org.anilmisirlioglu.keystroke.utils.BitMask

@State(
    name = "KeystrokeSettings",
    storages = [
        Storage("keystroke.settings.xml")
    ]
)
class SettingsService : PersistentStateComponent<Settings>{

    companion object{
        val instance: SettingsService = ServiceManager.getService(SettingsService::class.java)
    }

    private var state: Settings = Settings()

    override fun getState(): Settings = state

    override fun loadState(state: Settings){
        this.state = state
    }

    var isCountOnlyWorkspace: Boolean
        get() = state.countOnlyWorkspace
        set(value){
            state.countOnlyWorkspace = value
        }

    var dailyTarget: Int
        get() = state.dailyTarget
        set(value){
            state.dailyTarget = value
        }

    var isAllowedOtherKeys: Boolean
        get() = BitMask.has(KeyboardFlags.OTHER_KEYS, state.keyboardFlags)
        set(value){
            setKeyboardFlags(value, KeyboardFlags.OTHER_KEYS)
        }

    var isAllowedFunctionKeys: Boolean
        get() = BitMask.has(KeyboardFlags.FUNCTION_KEYS, state.keyboardFlags)
        set(value){
            setKeyboardFlags(value, KeyboardFlags.FUNCTION_KEYS)
        }

    var isAllowedCursorControlKeys: Boolean
        get() = BitMask.has(KeyboardFlags.CURSOR_CONTROL_KEYS, state.keyboardFlags)
        set(value){
            setKeyboardFlags(value, KeyboardFlags.CURSOR_CONTROL_KEYS)
        }

    private fun setKeyboardFlags(value: Boolean, flag: Int){
        state.keyboardFlags = if(value){
            BitMask.add(flag, state.keyboardFlags)
        }else{
            BitMask.delete(flag, state.keyboardFlags)
        }
    }

}