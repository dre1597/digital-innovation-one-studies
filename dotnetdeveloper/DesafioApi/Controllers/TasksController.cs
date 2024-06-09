using DesafioApi.Context;
using DesafioApi.Models;
using Microsoft.AspNetCore.Mvc;

namespace DesafioApi.Controllers;

[ApiController]
[Route("[controller]")]
public class TasksController(OrganizerContext context) : ControllerBase
{
    [HttpGet]
    public IActionResult FindAll()
    {
        var tasks = context.Tasks.ToList();
        return Ok(tasks);
    }

    [HttpGet("{id:int}")]
    public IActionResult FindById(int id)
    {
        var task = context.Tasks.Find(id);

        if (task == null)
        {
            return NotFound();
        }

        return Ok(task);
    }

    [HttpGet("/title/{title}")]
    public IActionResult FindByTitle(string title)
    {
        var lowerTitle = title.ToLower();
        var task = context.Tasks
            .Where(t => t.Title.ToLower().Contains(lowerTitle))
            .ToList();
        return Ok(task);
    }

    [HttpGet("/status/{status}")]
    public IActionResult FindByStatus(Status status)
    {
        var task = context.Tasks.Where(t => t.Status == status).ToList();
        return Ok(task);
    }

    [HttpGet("/date/{date:datetime}")]
    public IActionResult FindByDate(DateTime date)
    {
        var task = context.Tasks.Where(t => t.Date == date).ToList();
        return Ok(task);
    }

    [HttpPost]
    public IActionResult Create(TaskEntity task)
    {
        if (task.Date == DateTime.MinValue)
        {
            return BadRequest(new {Error = "The task date is required"});
        }
        context.Tasks.Add(task);
        context.SaveChanges();
        return CreatedAtAction(nameof(FindById), new { id = task.Id }, task);
    }

    [HttpPut("{id:int}")]
    public IActionResult Update(int id, TaskEntity task)
    {
        var existingTask = context.Tasks.Find(id);

        if (existingTask == null)
        {
            return NotFound();
        }

        if (task.Date == DateTime.MinValue)
        {
            return BadRequest(new {Error = "The task date is required"});
        }

        existingTask.Title = task.Title;
        existingTask.Description = task.Description;
        existingTask.Date = task.Date;
        existingTask.Status = task.Status;

        context.Tasks.Update(existingTask);
        context.SaveChanges();

        return NoContent();
    }

    [HttpDelete("{id:int}")]
    public IActionResult Delete(int id)
    {
        var task = context.Tasks.Find(id);
        if (task == null)
        {
            return NotFound();
        }

        context.Tasks.Remove(task);
        context.SaveChanges();

        return NoContent();
    }
}
