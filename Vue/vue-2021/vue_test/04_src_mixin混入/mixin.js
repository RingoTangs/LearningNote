const mixin = {
    methods: {
        showName(event) {
            alert('name = ' + this.name)
        },
    },
    mounted() {
        console.log('mounted')
    },
}

const mixin2 = {
    data() {
        return {
            x: 100,
            y: 200,
        }
    },
}

export { mixin, mixin2 }