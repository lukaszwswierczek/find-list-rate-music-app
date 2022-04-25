<div class="rating">
    <form:form class="form-check-inline" action="/user/tracks/edit-rating" method="post" modelAttribute="updateRating">
        <form:hidden path="id" value="${track.rating.id}"/>
        <form:radiobutton path="rating" class="rating__control screen-reader"
                          id="rc1-${track.rating.id}" value="1"/>
        <form:radiobutton path="rating" class="rating__control screen-reader"
                          id="rc2-${track.rating.id}" value="2"/>
        <form:radiobutton path="rating" class="rating__control screen-reader"
                          id="rc3-${track.rating.id}" value="3"/>
        <form:radiobutton path="rating" class="rating__control screen-reader"
                          id="rc4-${track.rating.id}" value="4"/>
        <form:radiobutton path="rating" class="rating__control screen-reader"
                          id="rc5-${track.rating.id}" value="5"/>
        <label for="rc1-${track.rating.id}" class="rating__item">
            <svg class="rating__star">
                <use xlink:href="#star"></use>
            </svg>
            <span class="screen-reader">1</span>
        </label>
        <label for="rc2-${track.rating.id}" class="rating__item">
            <svg class="rating__star">
                <use xlink:href="#star"></use>
            </svg>
            <span class="screen-reader">2</span>
        </label>
        <label for="rc3-${track.rating.id}" class="rating__item">
            <svg class="rating__star">
                <use xlink:href="#star"></use>
            </svg>
            <span class="screen-reader">3</span>
        </label>
        <label for="rc4-${track.rating.id}" class="rating__item">
            <svg class="rating__star">
                <use xlink:href="#star"></use>
            </svg>
            <span class="screen-reader">4</span>
        </label>
        <label for="rc5-${track.rating.id}" class="rating__item">
            <svg class="rating__star">
                <use xlink:href="#star"></use>
            </svg>
            <span class="screen-reader">5</span>
        </label>
    </div>
        <div>
        <button class="btn-outline-dark btn-block bg-transparent" type="submit"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check" viewBox="0 0 16 16">
            <path d="M10.97 4.97a.75.75 0 0 1 1.07 1.05l-3.99 4.99a.75.75 0 0 1-1.08.02L4.324 8.384a.75.75 0 1 1 1.06-1.06l2.094 2.093 3.473-4.425a.267.267 0 0 1 .02-.022z"/>
        </svg>
        </button>
    </form:form>
</div>

<svg xmlns="http://www.w3.org/2000/svg" style="display: none">
    <symbol id="star" viewBox="0 0 26 28">
        <path d="M26 10.109c0 .281-.203.547-.406.75l-5.672 5.531 1.344 7.812c.016.109.016.203.016.313 0 .406-.187.781-.641.781a1.27 1.27 0 0 1-.625-.187L13 21.422l-7.016 3.687c-.203.109-.406.187-.625.187-.453 0-.656-.375-.656-.781 0-.109.016-.203.031-.313l1.344-7.812L.39 10.859c-.187-.203-.391-.469-.391-.75 0-.469.484-.656.875-.719l7.844-1.141 3.516-7.109c.141-.297.406-.641.766-.641s.625.344.766.641l3.516 7.109 7.844 1.141c.375.063.875.25.875.719z"/>
    </symbol>
</svg>
