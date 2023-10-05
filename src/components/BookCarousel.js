import React from 'react';
import { Carousel } from 'antd';

function BookCarousel() {
    const createContent = (ctx) => {
        const images = ctx.keys().map(ctx);
        return images.map((img, i) => <div key={i}><img alt={i.toString()} src={img} /></div>);
    };

    const requireContext = require.context("../assets/carousel", true, /^\.\/.*\.jpg$/);

    return (
        <Carousel autoplay>
            {createContent(requireContext)}
        </Carousel>
    );
}

export default BookCarousel;
