package com.yapp.timitimi.presentation.ui.projectlist.adapter

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.drawable.Drawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.yapp.timitimi.domain.entity.Participant
import com.yapp.timitimi.presentation.databinding.ViewholderParticipantExtrasBinding
import com.yapp.timitimi.presentation.databinding.ViewholderParticipantProfileBinding
import com.yapp.timitimi.presentation.databinding.ViewholderProjectItemBinding
import com.yapp.timitimi.presentation.ui.projectlist.model.ProjectListItem
import com.yapp.timitimi.util.dp

class ProjectsItemViewHolder private constructor(
    private val binding: ViewholderProjectItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.participantsView.removeAllViews()
    }

    fun bind(item: ProjectListItem) {
        binding.item = item
        bindParticipants(item.participant)
        bindBackground(item.background)
        binding.executePendingBindings()
    }

    private fun bindBackground(backgroundUrl: String) {
        Glide.with(binding.container)
            .load(backgroundUrl)
            .transform(CenterCrop(), RoundedCorners(16.dp(binding.root.context)))
            .into(object : CustomTarget<Drawable>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    binding.container.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    binding.container.background = null
                }
            })
    }

    private fun bindParticipants(participants: List<Participant>) {
        val sortedTargetList = if (participants.size >= 3) {
            participants.subList(0, 3).sortedByDescending { it.isLeader }
        } else participants


            sortedTargetList
                .mapIndexed { index, participant ->
                    val profileBinding = ViewholderParticipantProfileBinding.inflate(
                        LayoutInflater.from(binding.root.context),
                        binding.participantsView,
                        false
                    ).apply {
                        crwon.isVisible = participant.isLeader
                        profileImage.loadCircularImage(borderSize = 2f, model = participant.imageUrl)

                        val layoutParams = root.layoutParams as FrameLayout.LayoutParams
                        //Thumbnail Image 크기(24dp) 만큼 End margin, thumbnail 끼리 겹칠 수 있도록 8dp 빼기
                        if (sortedTargetList.size != 1) {
                            layoutParams.setMargins(0, 0, (24 * (3 - index) - 8 * (3 - index)).dp(root.context), 0)
                        }
                        layoutParams.gravity = Gravity.END or Gravity.BOTTOM
                        root.layoutParams = layoutParams
                    }

                    binding.participantsView.addView(profileBinding.root)
                }

        if (participants.size > 3) {
            val extraBinding = ViewholderParticipantExtrasBinding.inflate(
                LayoutInflater.from(binding.root.context),
                binding.participantsView,
                false
            ).apply {
                extraText.text = "+${participants.size - 3}"
                val layoutParams = root.layoutParams as FrameLayout.LayoutParams
                layoutParams.gravity = Gravity.END or Gravity.BOTTOM
                root.layoutParams = layoutParams
            }

            binding.participantsView.addView(extraBinding.root)
        }
    }

    companion object {
        fun from(parent: ViewGroup): ProjectsItemViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ViewholderProjectItemBinding.inflate(layoutInflater, parent, false)
            return ProjectsItemViewHolder(binding)
        }
    }
}

// reference: https://debbi.tistory.com/19
private fun <T> ImageView.loadCircularImage(
    model: T,
    borderSize: Float = 0F,
    borderColor: Int = Color.WHITE
) {
    Glide.with(context)
        .asBitmap()
        .load(model)
        .apply(RequestOptions.circleCropTransform())
        .into(object : BitmapImageViewTarget(this) {
            override fun setResource(resource: Bitmap?) {
                setImageDrawable(
                    resource?.run {
                        RoundedBitmapDrawableFactory.create(
                            resources,
                            if (borderSize > 0) {
                                createBitmapWithBorder(borderSize, borderColor)
                            } else {
                                this
                            }
                        ).apply {
                            isCircular = true
                        }
                    }
                )
            }
        })
}

private fun Bitmap.createBitmapWithBorder(borderSize: Float, borderColor: Int): Bitmap {
    val borderOffset = (borderSize * 2).toInt()
    val halfWidth = width / 2
    val halfHeight = height / 2
    val circleRadius = Math.min(halfWidth, halfHeight).toFloat()
    val newBitmap = Bitmap.createBitmap(
        width + borderOffset,
        height + borderOffset,
        Bitmap.Config.ARGB_8888
    )

    // Center coordinates of the image
    val centerX = halfWidth + borderSize
    val centerY = halfHeight + borderSize

    val paint = Paint()
    val canvas = Canvas(newBitmap).apply {
        // Set transparent initial area
        drawARGB(0, 0, 0, 0)
    }

    // Draw the transparent initial area
    paint.isAntiAlias = true
    paint.style = Paint.Style.FILL
    canvas.drawCircle(centerX, centerY, circleRadius, paint)

    // Draw the image
    paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    canvas.drawBitmap(this, borderSize, borderSize, paint)

    // Draw the createBitmapWithBorder
    paint.xfermode = null
    paint.style = Paint.Style.STROKE
    paint.color = borderColor
    paint.strokeWidth = borderSize
    canvas.drawCircle(centerX, centerY, circleRadius, paint)
    return newBitmap
}