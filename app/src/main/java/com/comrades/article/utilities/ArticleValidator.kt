package com.comrades.article.utilities

import android.widget.EditText

object ArticleValidator {

    private const val CAPTION_MAX_LENGTH: Int = 100

    fun validateArticle(titleEditText: EditText,
                        captionEditText: EditText,
                        descriptionEditText: EditText,
                        contentEditText: EditText) : Boolean {

        return validateTitle(titleEditText) && validateCaption(captionEditText) && validateDescription(descriptionEditText) && validateContent(contentEditText)

    }

    private fun validateTitle(titleEditText: EditText) : Boolean {
        val isFilled: Boolean =  titleEditText.text.toString() != ""
        if (!isFilled) titleEditText.error = "Введите текст!"
        return isFilled
    }

    private fun validateCaption(captionEditText: EditText) : Boolean {
        val isFilled: Boolean =  captionEditText.text.toString() != ""
        val isLengthValid: Boolean = captionEditText.text.toString().length <= CAPTION_MAX_LENGTH
        if (!isFilled) captionEditText.error = "Введите текст!"
        if (!isLengthValid) captionEditText.error = "Введите не более $CAPTION_MAX_LENGTH символов!"
        return isFilled && isLengthValid
    }

    private fun validateDescription(descriptionEditText: EditText) : Boolean {
        val isFilled: Boolean =  descriptionEditText.text.toString() != ""
        if (!isFilled) descriptionEditText.error = "Введите текст!"
        return isFilled
    }

    private fun validateContent(contentEditText: EditText) : Boolean {
        val isFilled: Boolean =  contentEditText.text.toString() != ""
        if (!isFilled) contentEditText.error = "Введите текст!"
        return isFilled
    }

}