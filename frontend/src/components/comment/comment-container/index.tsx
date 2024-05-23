import React from 'react';
import styles from './index.module.scss';
import CommentEditable from "../comment-editable";
import CommentNotEditable from "../comment-not-editable";

function getStars(rating : number) {
    let stars: string[] = [];
    while (rating >= 1) {
        stars.push("/star-full.png");
        rating = rating - 1;
    }

    if (rating >= 0.5) {
        stars.push("/star-half.png");
    }

    while (stars.length < 5) {
        stars.push("/star-empty.png");
    }

    return stars;
}

function getStarsDiv(stars: string[]) {
    let divs: JSX.Element[] = [];

    for (let star of stars) {
        divs.push(<img src={star} className={styles["star-icon"]} />)
    }

    return divs;
}

function CommentContainer({productId}: {productId: String}) {

    const userName = "<Username>";
    const userRating = 3.5;
    const commentDate = '01.01.2024'
    const userComment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta. Mauris massa. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio.";

    return(
        <div className={styles['comments-container']}>
            <CommentEditable commentID={"123"}></CommentEditable>
            <CommentNotEditable commentID={"123"}></CommentNotEditable>
        </div>
    )
}

export default CommentContainer;