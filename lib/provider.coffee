fs = require 'fs'
path = require 'path'
console.log "Workin!"
module.exports =
  selector: '.text.html'
  filterSuggestion: true
  getSuggestions: (req) ->
    console.log  req
    {prefix} = req
    if req.prefix == ""
      console.log "You typed a paren!"
      @getRacketFunctions()
  onDidInsertSuggestion: ({editor, suggestion}) ->
    console.log "Suggestion"
    setTimeout(@triggerAutocomplete.bind(this, editor), 1)

  triggerAutocomplete: (editor)  ->
    atom.commands.dispatch(atom.views.getView(editor), 'autocomplete-plus:activate', activatedManually: false)
  loadCompletions: ->
    console.log "completions loaded"
    @completions = {}
    fs.readFile path.resolve(__dirname, '..', 'completions.json'), (error, content) =>
      @completions = JSON.parse(content) unless error?
      return
  getFunctions: (f) ->
    f = @completions.functions[f]
  getRacketFunctions: (prefix) ->
    completions = []
    for f in @completions.functions
      completions.push(@buildRacketFunctionCompletion(f))
    completions
  buildRacketFunctionCompletion: (f) ->
    console.log "fns built"
    text: f
    type: 'function'
    description: "Racket #{f} function"
