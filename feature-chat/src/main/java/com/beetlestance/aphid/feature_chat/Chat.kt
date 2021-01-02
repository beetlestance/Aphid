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
package com.beetlestance.aphid.feature_chat

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.node.Ref
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.beetlestance.aphid.base.CHAT_MESSAGE_ANSWER
import com.beetlestance.aphid.common_compose.insets.AmbientWindowInsets
import com.beetlestance.aphid.common_compose.insets.imePadding
import com.beetlestance.aphid.common_compose.insets.statusBarsPadding
import com.beetlestance.aphid.data.entities.Chat
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun Chat(
    viewModel: ChatViewModel,
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues()
) {
    val state by viewModel.liveData.observeAsState(initial = viewModel.currentState())
    val action: (ChatActions) -> Unit = { action -> viewModel.submitAction(action) }

    val isImeVisible = AmbientWindowInsets.current.ime.isVisible

    val transformedPadding = if (isImeVisible) PaddingValues(bottom = 16.dp) else paddingValues

    Chat(
        paddingValues = transformedPadding,
        state = state,
        action = action,
        modifier = modifier
    )
}

/**
 * Entry point for chat screen.
 *
 * @param paddingValues contains padding for bottom navigation
 * @param state contains the state for the UI to draw
 * @param action is an block to perform [ChatActions]
 * @param modifier [Modifier] to apply to this layout node
 */
@Composable
private fun Chat(
    paddingValues: PaddingValues,
    state: ChatViewState,
    action: (ChatActions) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.surface.copy(alpha = 0.95f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .statusBarsPadding()
                .imePadding()
        ) {

            // Box will draw each element on top of each other
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    if (state.messages.isNullOrEmpty()) {
                        EmptyChat(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 64.dp)
                                .align(Alignment.CenterHorizontally)
                                .weight(1f)
                        )
                    } else {
                        ChatListing(
                            messages = state.messages,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                                .weight(1f)
                        )
                    }
                }

                // This will float over the messages
                ChatInput(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = 16.dp),
                    onQuerySubmit = {
                        if (it.isNotBlank()) action(ChatActions.SendMessage(it))
                    }
                )
            }
        }
    }
}

@Composable
fun ChatListing(
    messages: List<Chat>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        content = {
            items(messages) { message ->
                if (message.type == CHAT_MESSAGE_ANSWER) {
                    ReplyItem(answer = message)
                } else {
                    AskedQuestionsItem(question = message)
                }
            }
        }
    )
}

@Composable
fun AskedQuestionsItem(question: Chat) {
    if (question.message.isNullOrBlank()) return

    Surface(
        shape = circleShapeWithBottomRightCorner,
        modifier = Modifier.padding(16.dp)
    ) {
        Box {
            Row(
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    text = question.message ?: return@Row,
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp)
                        .align(Alignment.CenterVertically),
                    color = Color.Gray
                )

                // TODO: Load user image here
                CoilImage(
                    data = "",
                    modifier = Modifier
                        .preferredHeight(64.dp)
                        .aspectRatio(1 / 1f)
                        .padding(8.dp)
                        .border(
                            border = BorderStroke(2.dp, color = MaterialTheme.colors.primary),
                            shape = CircleShape
                        )
                        .background(
                            color = Color.LightGray,
                            shape = CircleShape
                        )
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
fun ReplyItem(answer: Chat) {
    Surface(
        shape = circleShapeWithTopRightCorner,
        modifier = Modifier.padding(16.dp)
    ) {
        Box {
            Row(
                modifier = Modifier
            ) {

                CoilImage(
                    data = R.drawable.ic_chat_bot,
                    modifier = Modifier
                        .preferredHeight(64.dp)
                        .padding(16.dp)
                )

                Text(
                    text = answer.message ?: "Sorry I am not smart enough to answer this.",
                    modifier = Modifier
                        .weight(1f)
                        .padding(16.dp),
                    color = Color.Gray
                )

                if (answer.image.isNullOrBlank().not()) {
                    CoilImage(
                        data = answer.image ?: "",
                        modifier = Modifier
                            .fillMaxHeight()
                            .align(Alignment.CenterVertically)
                            .padding(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyChat(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .weight(1f)
        ) {
            Image(
                imageVector = vectorResource(id = R.drawable.ic_chat_empty),
                modifier = Modifier
                    .padding(top = 32.dp)
                    .align(Alignment.TopCenter)
            )
        }

        EmptyChatHint(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
    }
}

@Composable
fun EmptyChatHint(
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp,
    shape: Shape = circleShapeWithTopRightCorner
) {
    Surface(
        modifier = modifier,
        elevation = elevation,
        shape = shape
    ) {
        Row {
            Image(
                modifier = Modifier.padding(16.dp)
                    .size(48.dp),
                imageVector = vectorResource(id = R.drawable.ic_chat_bot),
                alignment = Alignment.Center
            )

            Column(
                modifier = Modifier.padding(vertical = 16.dp),
            ) {
                Text(
                    text = "Have a delicious question?",
                    style = MaterialTheme.typography.h6
                )

                Text(
                    text = "try \" Calories in Brown Bread\"",
                    fontWeight = FontWeight.ExtraLight
                )
            }
        }
    }
}

@Composable
private fun ChatInput(
    modifier: Modifier = Modifier,
    state: InputState = rememberInputState(),
    onQuerySubmit: (String) -> Unit
) {
    // reference for the keyboard
    // to be used for hiding key board
    val keyboardController: Ref<SoftwareKeyboardController> = remember { Ref() }

    Surface(
        shape = RoundedCornerShape(
            topLeftPercent = 50,
            topRightPercent = 50,
            bottomLeftPercent = 50,
            bottomRightPercent = 0
        ),
        elevation = 4.dp,
        modifier = modifier
            .fillMaxWidth()
            .preferredHeight(56.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center

        ) {
            if (state.query.text.isEmpty()) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 32.dp)
                        .fillMaxWidth()
                        .wrapContentSize()
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Left,
                        text = "Type here...",
                        color = Color.Gray
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                BasicTextField(
                    value = state.query,
                    onValueChange = {
                        state.query = it
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    onImeActionPerformed = {
                        // Change: Find out why state clear is not working here
                        // Theory: Composable is recomposing which is resulting in state side effects
                        keyboardController.value?.hideSoftwareKeyboard()
                    },
                    onTextInputStarted = {
                        keyboardController.value = it
                    },
                    modifier = Modifier
                        .weight(1f)
                )

                Icon(
                    imageVector = vectorResource(id = R.drawable.ic_chat_input_send),
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(
                            onClick = {
                                onQuerySubmit(state.query.text)
                                state.clear()
                            }
                        )
                        .padding(8.dp)
                )
            }
        }
    }
}

@Composable
private fun rememberInputState(
    query: TextFieldValue = TextFieldValue("")
): InputState {
    return remember {
        InputState(
            query = query
        )
    }
}

@Stable
class InputState(
    query: TextFieldValue
) {
    var query: TextFieldValue by mutableStateOf(query)

    @Stable
    fun clear() {
        query = TextFieldValue("")
    }
}

val circleShapeWithTopRightCorner: RoundedCornerShape = RoundedCornerShape(
    topLeft = 0.dp,
    topRight = 32.dp,
    bottomLeft = 32.dp,
    bottomRight = 32.dp
)

val circleShapeWithBottomRightCorner: RoundedCornerShape = RoundedCornerShape(
    topLeft = 32.dp,
    topRight = 32.dp,
    bottomLeft = 32.dp,
    bottomRight = 0.dp
)
