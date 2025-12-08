package com.echo.kotlinlearning.service

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

data class Book(
    val id: Int,
    val title: String,
    val author: String
)


// 根节点：必须添加无参构造，strict=false忽略多余节点
@Root(name = "fileList", strict = false)
class FileList {
    // 子节点列表：name="file" 对应XML中的<file>节点，inline=true去掉外层<List>
    // 必须用var + lateinit（无参构造需要）
    @ElementList(inline = true, name = "file")
    lateinit var files: List<File>

    // 必须显式声明无参构造（Simple XML反射用）
    constructor()

    // 可选：带参构造（方便代码中创建）
    constructor(files: List<File>) {
        this.files = files
    }
}

// 单个file节点：同样添加无参构造
@Root(name = "file", strict = false)
class File {
    @Element(name = "id")
    var id: Int = 0 // 用默认值替代lateinit（基本类型不能lateinit）

    @Element(name = "title")
    lateinit var title: String

    @Element(name = "author")
    lateinit var author: String

    // 无参构造（必须）
    constructor()

    // 带参构造（可选）
    constructor(id: Int, title: String, author: String) {
        this.id = id
        this.title = title
        this.author = author
    }
}