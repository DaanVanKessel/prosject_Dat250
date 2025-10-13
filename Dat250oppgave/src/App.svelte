<script>
    /*import './PollController.java';*/
    let result = $state(fetch("/User").then((response) => {
            return response.json();
        }
    ))
</script>

<main>
  <div>
    <a href="https://vite.dev" target="_blank" rel="noreferrer">

    </a>
    <a href="https://svelte.dev" target="_blank" rel="noreferrer">

    </a>
  </div>
    <button>

    </button>
  <h1>Vite {result} + Svelte</h1>

  <div class="card">
  </div>



</main>
