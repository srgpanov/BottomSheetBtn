package com.example.bottomsheetbtn

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.example.bottomsheetbtn.databinding.DialogLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.lang.Float.min
import kotlin.math.max


class BottomSheetDialog : BottomSheetDialogFragment() {
    private var _binding: DialogLayoutBinding? = null
    private val binding: DialogLayoutBinding
        get() = _binding!!

    private  var btnTopMargin:Int =0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnTopMargin = dpToPx(16)
        val adapter = Adapter()
        binding.rv.adapter = adapter
        binding.btn.setOnClickListener {
            adapter.items++
        }
    }
    fun dpToPx(dp: Int): Int {
        val displayMetrics = context!!.resources.displayMetrics
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    override fun onStart() {
        super.onStart()
        setupBehaviour()
    }

    private fun setupBehaviour() {
        val bottomSheet = requireDialog().findViewById<FrameLayout>(R.id.design_bottom_sheet)
        val behavior: BottomSheetBehavior<*> = BottomSheetBehavior.from(bottomSheet)
        behavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                calculateTranslation(behavior, bottomSheet, slideOffset)
            }

        })
    }

    fun calculateTranslation(behavior: BottomSheetBehavior<*>, view: View, slideOffset: Float) {

        val height = view.height
        val expandedOffset = behavior.expandedOffset
        val peekHeight = (height + expandedOffset) / 16 * 9
        behavior.peekHeight = peekHeight


        if (height < peekHeight) {
            binding.btn.translationY = slideOffset * height
        } else {
            val offset  = peekHeight - height

            val delta = calculateDelta(height, peekHeight, slideOffset)
            val translation = offset.toFloat() + delta
            binding.btn.translationY = clampBtnTranslation(translation,height)

        }
    }
    private fun clampBtnTranslation(translation: Float, viewHeight: Int): Float {
        val offset = viewHeight + translation
        val btnMinOffset = binding.btn.height + btnTopMargin
        if (offset >btnMinOffset){
            return  translation
        }else{
            return translation-offset +btnMinOffset
        }
    }

    private fun calculateDelta(
        height: Int,
        peekHeight: Int,
        slideOffset: Float
    ): Float {
        if (slideOffset>0){
            return (height - peekHeight) * slideOffset
        }else{
            return peekHeight*slideOffset
        }

    }


}