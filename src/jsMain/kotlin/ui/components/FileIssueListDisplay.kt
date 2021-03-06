package ui.components

import css.FileErrorDisplayStyle
import model.ValidationOutcome
import react.*
import styled.css
import styled.styledDiv

external interface FileIssueListDisplayProps : RProps {
    var validationOutcome: ValidationOutcome
}

class FileIssueListDisplayState : RState {

}

class FileIssueListDisplayComponent : RComponent<FileIssueListDisplayProps, FileIssueListDisplayState>() {
    init {
        state = FileIssueListDisplayState()
    }

    override fun RBuilder.render() {
        styledDiv {
            css {
                +FileErrorDisplayStyle.mainDivList
            }
            props.validationOutcome.getMessages().sortedBy { it.getLine() }.forEach {
                messageDisplayComponent {
                    validationMessage = it
                }
            }
        }
    }
}

fun RBuilder.fileIssueListDisplayComponent(handler: FileIssueListDisplayProps.() -> Unit): ReactElement {
    return child(FileIssueListDisplayComponent::class) {
        this.attrs(handler)
    }
}

