// <meta charSet="utf-8">
//     <script type="text/javascript">
// </script>
//  js> JS user.js>...
    {
        formElem.onsubmit = async (e) => {
        e.preventDefault();

        let response = await fetch('/article/formdata/post/user', {
        method: 'POST',
        body: new FormData(formElem)
        });

        let result = await response.json();

        alert(result.message);
        }
    }

// let a = 'Bob';
// let b = '30';
// let c = 'Bob';
// let  d = ['Bob',30,'go'];
// console.log(d)