package com.mohdroid.domain.dto.repositories


data class UserRepositoriesResponse(
    val totalCount: Int,
    val edges: List<Node>
)

data class Node(
    val name: String?,
    val stargazerCount: Int?
)

data class Edge(
    val cursor: String,
    val node: Node
)

