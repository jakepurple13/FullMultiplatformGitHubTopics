package com.programmersbox.shared.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.programmersbox.shared.network.GitHubTopic

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopicItem(
    item: GitHubTopic,
    savedTopics: List<String>,
    currentTopics: List<String>,
    onCardClick: (GitHubTopic) -> Unit,
    onTopicClick: (String) -> Unit,
    isFavorite: Boolean,
    onFavoriteClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    //val actions = LocalAppActions.current
    OutlinedCard(
        onClick = { onCardClick(item) },
        modifier = modifier.padding(horizontal = 4.dp)
    ) {
        Column(modifier = Modifier.padding(4.dp)) {
            ListItem(
                headlineText = { Text(item.name) },
                overlineText = {
                    Text(
                        item.fullName,
                        textDecoration = TextDecoration.Underline
                    )
                },
                supportingText = { item.description?.let { Text(it) } },
                leadingContent = {
                    Surface(shape = CircleShape) {
                        /*KamelImage(
                            lazyPainterResource(item.owner.avatarUrl.orEmpty()),
                            modifier = Modifier.size(48.dp),
                            contentDescription = null,
                            animationSpec = tween()
                        )*/
                        Icon(Icons.Default.CatchingPokemon, null, Modifier.size(48.dp))
                    }
                },
                trailingContent = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        //IconsButton(onClick = { actions.onShareClick(item) }, icon = Icons.Default.Share)

                        IconsButton(
                            onClick = { onFavoriteClick(!isFavorite) },
                            icon = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, contentDescription = null)
                            Text(item.stars.toString())
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.ForkLeft, contentDescription = null)
                            Text(item.forks.toString())
                        }
                    }
                }
            )

            FlowRow(modifier = Modifier.padding(4.dp)) {
                item.topics.forEach {
                    AssistChip(
                        label = { Text(it) },
                        modifier = Modifier.padding(2.dp),
                        onClick = { onTopicClick(it) },
                        leadingIcon = { Icon(Icons.Default.CatchingPokemon, null, modifier = Modifier.rotate(180f)) }
                    )
                }
            }

            item.license?.let {
                Text(
                    it.name,
                    modifier = Modifier.padding(4.dp)
                )
            }

            Row {
                Text(
                    text = item.pushedAt,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                )

                Text(
                    text = item.language,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(4.dp)
                        .weight(1f)
                )
            }
        }
    }
}