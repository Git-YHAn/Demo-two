export default function ({ store, route, redirect }) {
  if (process.server || !window) return
  if (!store.state.token) {
    return redirect('/login')
  }
}
