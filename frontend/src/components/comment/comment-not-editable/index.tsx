import React from 'react';
import styles from './index.module.scss';

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

function CommentNotEditable({commentID}: {commentID: String}) {

    const userName = "<Username>";
    const userRating = 3;
    const commentDate = '01.01.2024'
    const userComment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum. Praesent mauris. Fusce nec tellus sed augue semper porta. Mauris massa. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio.";

    return (
        <div className={styles['comment-container']}>
            <div className={styles['user-container']}>
                <img className={styles['user-image']} src={"/avatar.png"}/>
                <div className={styles['user-name']}>{userName}</div>
            </div>
            <div className={styles['description-container']}>
                <div className={styles['rating-and-date']}>
                    <div className={styles['comment-rating']}>
                        <div className={styles.stars}>{getStarsDiv(getStars(userRating))} </div>
                        <div className={styles['rating-text']}>{userRating}</div>
                    </div>
                    <div className={styles['date']}>{commentDate}</div>
                </div>
                <div className={styles['comment-text']}>{userComment}</div>
            </div>
        </div>
    )
}

export default CommentNotEditable;